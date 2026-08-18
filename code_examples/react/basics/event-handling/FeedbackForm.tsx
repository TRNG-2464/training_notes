/*
    Showcases onSubmit event using a form,
    referencing the React.SubmitEvent SyntheticEvent Type
*/
import { useState } from "react";

function FeedbackForm() {
  const [feedback, setFeedback] = useState("");

  function handleSubmit(event: React.SubmitEvent<HTMLFormElement>) {
    event.preventDefault(); // prevents the page from reloading
    console.log("Feedback submitted:", feedback);
  }

  return (
    <form onSubmit={handleSubmit}>
      <textarea
        value={feedback}
        onChange={(e) => setFeedback(e.target.value)}
        placeholder="Leave your feedback..."
      />
      <button type="submit">Submit</button>
    </form>
  );
}

export default FeedbackForm;