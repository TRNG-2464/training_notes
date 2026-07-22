#!/bin/bash

# && only runs mkdir if the 'cd' command succeeds
cd my-project && mkdir logs

# || only runs the echo command if 'cd' FAILS (e.g., folder doesn't exist)
cd nonexistent-folder || echo "Error: that folder does not exist"

# Using an if statement with a condition to check the first argument
if [ "$1" == "start" ]; then
    echo "Starting the process..."
elif [ "$1" == "stop" ]; then
    echo "Stopping the process..."
else
    echo "Unknown command. Please use 'start' or 'stop'."
fi