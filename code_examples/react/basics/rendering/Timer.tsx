/*
    This component showcases a 'useEffect' on UnMount.
    i.e. when the component needs to stop being rendered
*/
import { useEffect, useState } from "react";

function Timer() {
    const [seconds, setSeconds] = useState(0);

    useEffect(() => {

        console.log("Timer Mounted - Starting Interval.")

        const intervalId = setInterval(() => {
            setSeconds((prev) => prev +1); 
        }, 1000);   // 1000 miliseconds i.e. every second

        /* 
            This returned function is a CLEANUP function - react
            call this automatically when the component unmounts,
            preventing the interval from continuing to run in 
            the background.
        */
        return () => {
            console.log("Timer unmounting - Clearing Interval.");
            clearInterval(intervalId);
        };
    }, []);

    return <p>Seconds elapsed: {seconds}</p>
}

export default Timer;