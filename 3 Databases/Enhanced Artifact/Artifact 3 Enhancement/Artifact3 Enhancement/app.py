# Mark Eilers
# CS 499: Senior Capstone
# Enhancement Artifact 3
# August 10, 2024

# Import necessary libraries for the web application
from jupyter_plotly_dash import JupyterDash

import dash
import dash_leaflet as dl
import dash_core_components as dcc
import dash_html_components as html
import plotly.express as px
import dash_table as dt
from dash.dependencies import Input, Output, State

import os
import numpy as np
import pandas as pd
from pymongo import MongoClient
from bson.json_util import dumps

# Custom CRUD class import (update to match the previous module name and class name)
from shelter_db_manager import ShelterDBManager
# Image encoding for logo display
import base64

##############################
# Data Handling / Preparation
##############################
# Update with your credentials and DB details
user = "aacuser"
pwd = "cs340"
db_name = "AAC"
shelter_db = ShelterDBManager(user, pwd, db_name)

# Convert MongoDB cursor result to DataFrame for Dash
animal_df = pd.DataFrame.from_records(shelter_db.read({}))

#########################
# Dashboard Layout / View
#########################

# If testing in a Jupyter Notebook:
# app = JupyterDash('Animal Rescue Dashboard')

# For terminal execution:
app = dash.Dash('Animal Rescue Dashboard')

# Encode logo image
logo_file = 'GraziosoSalvareLogo.png'
encoded_logo = base64.b64encode(open(logo_file, 'rb').read())

app.layout = html.Div([
    html.Div(id='hidden-div', style={'display': 'none'}),
    # Display the logo with an anchor tag linking to a client webpage
    html.Center([
        html.A([
            html.Img(id='logo-image',
                     src='data:image/png;base64,{}'.format(encoded_logo.decode()),
                     alt='Grazioso Salvare Logo',
                     style={'width': 225})
        ], href="https://www.arsari.us", target="_blank"),
        html.H1("Rescue Animal Search Dashboard"),
        html.H5("Developed by Mark Eilers", style={'color': 'green'})
    ]),
    html.Hr(),
    # Filtering options (buttons and dropdown)
    html.Div(className='row', style={'display': 'flex'}, children=[
        html.Span("Filter by:", style={'margin': 6}),
        html.Span(html.Button(id='cat-filter-btn', n_clicks=0, children='Cats'), style={'margin': 6}),
        html.Span(html.Button(id='dog-filter-btn', n_clicks=0, children='Dogs'), style={'margin': 6}),
        html.Span(html.Button(id='reset-filters', n_clicks=0, children='Reset', style={'background-color': 'red', 'color': 'white'}), style={'margin': 6}),
        html.Span("or", style={'margin': 6}),
        html.Span([
            dcc.Dropdown(
                id='category-filter',
                options=[
                    {'label': 'Water Rescue', 'value': 'wr'},
                    {'label': 'Mountain or Wilderness Rescue', 'value': 'mwr'},
                    {'label': 'Disaster Rescue or Individual Tracking', 'value': 'drit'}
                ],
                placeholder="Select a Dog Category Filter",
                style={'marginLeft': 5, 'width': 350}
            )
        ])
    ]),
    html.Hr(),
    # DataTable for displaying animal data
    dt.DataTable(
        id='animal-table',
        columns=[{"name": col, "id": col, "deletable": False, "selectable": True} for col in animal_df.columns],
        data=animal_df.to_dict('records'),
        editable=False,
        filter_action="native",
        sort_action="native",
        sort_mode="multi",
        column_selectable=False,
        row_selectable=False,
        row_deletable=False,
        selected_columns=[],
        selected_rows=[0],
        page_action="native",
        page_current=0,
        page_size=10
    ),
    html.Br(),
    html.Hr(),
    # Layout for side-by-side chart and map
    html.Div(className='row', style={'display': 'flex'}, children=[
        html.Div(id='chart-div', className='col s12 m6'),
        html.Div(id='map-div', className='col s12 m6')
    ]),
    # Footer with unique identifier
    html.Div([
        html.Hr(),
        html.P([
            "Module 7-2 Project Two Submission - Prof. Tad Kellogg M.S.",
            html.Br(),
            "CS-499 Senior Capstone Southern New Hampshire University",
            html.Br(),
            "February 21, 2021"
        ], style={'fontSize': 12})
    ])
])

#############################################
# Interaction Between Components / Controller
#############################################

# Callback to update data table based on selected filter
@app.callback(
    Output('animal-table', 'data'),
    [Input('category-filter', 'value'),
     Input('cat-filter-btn', 'n_clicks'),
     Input('dog-filter-btn', 'n_clicks')]
)
def filter_animals(selected_category, cats_btn, dogs_btn):
    if selected_category == 'drit':
        query = {
            "animal_type": "Dog",
            "breed": {"$in": ["Doberman Pinscher", "German Shepherd", "Golden Retriever", "Bloodhound", "Rottweiler"]},
            "sex_upon_outcome": "Intact Male",
            "age_upon_outcome_in_weeks": {"$gte": 20, "$lte": 300}
        }
    elif selected_category == 'mwr':
        query = {
            "animal_type": "Dog",
            "breed": {"$in": ["German Shepherd", "Alaskan Malamute", "Old English Sheepdog", "Siberian Husky", "Rottweiler"]},
            "sex_upon_outcome": "Intact Male",
            "age_upon_outcome_in_weeks": {"$gte": 26, "$lte": 156}
        }
    elif selected_category == 'wr':
        query = {
            "animal_type": "Dog",
            "breed": {"$in": ["Labrador Retriever Mix", "Chesapeake Bay Retriever", "Newfoundland"]},
            "sex_upon_outcome": "Intact Female",
            "age_upon_outcome_in_weeks": {"$gte": 26, "$lte": 156}
        }
    elif int(cats_btn) > int(dogs_btn):
        query = {"animal_type": "Cat"}
    elif int(dogs_btn) > int(cats_btn):
        query = {"animal_type": "Dog"}
    else:
        query = {}

    filtered_df = pd.DataFrame(list(shelter_db.read(query)))
    return filtered_df.to_dict('records')

# Callback to reset cat and dog filter buttons
@app.callback(
    [Output('cat-filter-btn', 'n_clicks'),
     Output('dog-filter-btn', 'n_clicks')],
    [Input('reset-filters', 'n_clicks')]
)
def reset_filters(reset_btn):
    return 0, 0

# Callback to highlight selected column or row in the data table
@app.callback(
    Output('animal-table', 'style_data_conditional'),
    [Input('animal-table', 'selected_columns'),
     Input('animal-table', "derived_viewport_selected_rows"),
     Input('animal-table', 'active_cell')]
)
def style_table(selected_columns, selected_rows, active_cell):
    if active_cell:
        highlight_style = [{'if': {'row_index': active_cell['row']}, 'background_color': '#a5d6a7'}]
    else:
        highlight_style = [{'if': {'row_index': i}, 'background_color': '#a5d6a7'} for i in selected_rows]

    return (highlight_style +
            [{'if': {'column_id': col}, 'background_color': '#80deea'} for col in selected_columns])

# Callback to generate pie chart based on table data
@app.callback(
    Output('chart-div', 'children'),
    [Input('animal-table', 'derived_viewport_data')]
)
def generate_pie_chart(view_data):
    df = pd.DataFrame(view_data)
    pie_chart = px.pie(df, names='breed', title='Animal Breeds Pie Chart')
    return [dcc.Graph(figure=pie_chart)]

# Callback to generate map based on selected row in the table
@app.callback(
    Output('map-div', 'children'),
    [Input('animal-table', 'derived_viewport_data'),
     Input('animal-table', 'derived_viewport_selected_rows'),
     Input('animal-table', 'active_cell')]
)
def update_map(view_data, selected_rows, active_cell):
    df = pd.DataFrame(view_data)

    if active_cell:
        selected_row = active_cell['row']
    else:
        selected_row = selected_rows[0]

    lat = df.loc[selected_row, 'location_lat']
    lon = df.loc[selected_row, 'location_long']
    name = df.loc[selected_row, 'name']
    breed = df.loc[selected_row, 'breed']
    animal_type = df.loc[selected_row, 'animal_type']
    age = df.loc[selected_row, 'age_upon_outcome']

    if not name:
        name = "No Name"

    return [
        dl.Map(style={'width': '1000px', 'height': '500px'}, center=[lat, lon], zoom=10, children=[
            dl.TileLayer(id="base-layer-id"),
            dl.Marker(position=[lat, lon], children=[
                dl.Tooltip(f"({lat:.3f}, {lon:.3f})"),
                dl.Popup([
                    html.H2(name),
                    html.P([html.Strong(f"{animal_type} | Age: {age}"), html.Br(), breed])
                ])
            ])
        ])
    ]

###############
# Run the app
###############
if __name__ == '__main__':
    app.run_server(debug=True)
