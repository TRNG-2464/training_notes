// A common, reusable set of props many components might accept.
interface BaseStyleProps {
  className?: string;
}

// "CustomButtonProps" builds on top of "BaseStyleProps," avoiding
// repetitive typing across multiple components.
interface CustomButtonProps extends BaseStyleProps {
  label: string;
  onClick: () => void;
}

function CustomButton({ label, onClick, className }: CustomButtonProps) {
  return (
    <button className={className} onClick={onClick}>
      {label}
    </button>
  );
}

export default CustomButton;