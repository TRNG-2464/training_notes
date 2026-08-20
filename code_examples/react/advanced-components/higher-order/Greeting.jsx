/*
    This is an ordinary component, but it will be wrapped
    in a border using the Higher-Order component 'withBorder'
*/
import withBorder from "./withBorder";

function Greeting({name}) {
    return <p>Hello, {name}!</p>;
}

/*
    Here, we are wrapping this component to create a NEW
    component. We could, in theory, do this wrapping elsewhere
    see the 'HigherOrderApp' for details
*/
const BorderedGreeting = withBorder(Greeting);

export  {Greeting, BorderedGreeting};