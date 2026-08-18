/*
    This component is the Parent to Timer - it will 
    be used to show the timer, and include a toggle
    so that we can see the unmount behavior print
    to the console.

    Quick Note: In the console, when toggling the timer
    you will see "Timer Mounted...Timer Unmounted... Timer Mounted"

    This is a quirk of the Dev runtime. Vite runs
    mounting/unmounting an extra time to help catch effects
    that don't cleaup properly during development, which
    might be hard to catch in a production build.
*/
import { useState } from "react";
import Timer from "./Timer";

function TimerToggle() {
    const [showTimer, setShowTimer] = useState(true);

    return (
        <div>
            <button onClick={() => setShowTimer(!showTimer)}>
                {/* 
                    This ternary will show different text on the button 
                    depending on the state of showTimer boolean 
                */}
                {showTimer ? "Stop and Remove Timer" : "Show Timer"}
            </button>

            {/* 
                Conditional Rendering is being used to determine if we actually
                show the timer. When showTimer flips to false, Timer disappears
                from the tree entirely 
            */}
            {showTimer && <Timer />}
        </div>
    );
}

export default TimerToggle;