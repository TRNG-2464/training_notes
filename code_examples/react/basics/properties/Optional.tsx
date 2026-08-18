interface OptionalProps {
    author: string;
    title: string;
    subtitle?: string; // The subtitle prop is optional, indicated by the "?" symbol.
}

function Optional({ author, title, subtitle }: OptionalProps) {
    return (
        <div>
            <h2>Title: {title}</h2>
            <p>Author: {author}</p>
            {subtitle && <p>Subtitle: {subtitle}</p>} {/* Conditionally render the subtitle if it exists */}
        </div>
    );
}

export default Optional;