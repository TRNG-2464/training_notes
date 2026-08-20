import withBorder from "./withBorder";
import { Greeting, BorderedGreeting as ImportedBorderedGreeting } from "./Greeting";

const WrappedBorderedGreeting = withBorder(Greeting);

function HigherOrderApp () {
    return (
        <div>
            {/* the plain Greeting imported with no border */}
            <Greeting />

            {/* The border-wrapped Greeting that was imported */}
            <ImportedBorderedGreeting />
            
            {/* The Wrapped version - wrapped in this file */}
            <WrappedBorderedGreeting />
        </div>
    );
}

export default HigherOrderApp;