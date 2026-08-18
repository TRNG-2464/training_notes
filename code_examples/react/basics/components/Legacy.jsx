/*
    Class-based component creation is considered a legacy
    creation method. This example is for your information
    but you should create function-based components as a
    standard practice.
*/
import React from 'react';

class Legacy extends React.Component {
    constructor(props) {
        super(props);
        // State is a single, nested object, not multiple useState calls
        this.state = {
            count: 0
        }
    }

    // Lifecycle methods
    componentDidMount() {
        console.log("Legacy has mounted!");
    }

    componentDidUpdate() {
        console.log("Legacy count updated. New count: ", this.state.count)
    }

    componentWillUnmount() {
        console.log("Legacy is about to unmount!");
    }

    handleIncrement = () => {
        this.setState( { count: this.state.count + 1} );
    }

    render() {
        return (
            <div>
                <p>Count: {this.state.count}</p>
                <button onClick={this.handleIncrement}>Increment (+)</button>
            </div>
        );
    }
}

/*
    The Modern (function-based component) equivalent would look like this:
    import { useState, useEffect} from "react";

    function Modern() {
        const [count, setCount] = useState(0);

        useEffect(
            () => {
                console.log("Modern Mounted!");
            

            return () => {
                console.log("Counter is about to unmount!");
            };
        }, []);

        useEffect(
            () => {
                console.log("Modern Updated. New Count:", count);
            }, [count]);

        function handleIncrement() {
            setCount(count + 1);
        }

        return (
            <div>
                <p>Count: {count}</p>
                <button onClick={handleIncrement}>Increment (+)</button>
            </div>
        );
    } 
    
*/


export default Legacy;