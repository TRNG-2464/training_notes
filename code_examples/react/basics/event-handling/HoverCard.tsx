/*
    Showcases onMouseEnter and onMouseExit events
*/
import { useState } from "react";

function HoverCard() {
  const [isHovering, setIsHovering] = useState(false);

  return (
    <div
      onMouseEnter={() => setIsHovering(true)}
      onMouseLeave={() => setIsHovering(false)}
      style={{
        padding: "20px",
        backgroundColor: isHovering ? "#ffd632" : "#f3f4f6",
        border: "1px solid #d1d5db",
        width: "200px",
      }}
    >
      {isHovering ? "You're hovering over me!" : "Hover over this card"}
    </div>
  );
}

export default HoverCard;