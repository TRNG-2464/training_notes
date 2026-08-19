/*
    A small, presentational component to display custom
    status text.
*/
interface StatusMessageProps {
  message: string;
  type: "loading" | "success" | "error" | "";
}

function StatusMessage({ message, type }: StatusMessageProps) {
  if (!message) {
    return null; // If there is no message, we have nothing to show (conditional rendering)
  }

  return <p className={`status ${type}`}>{message}</p>;
}

export default StatusMessage;
export type { StatusMessageProps };