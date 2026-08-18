interface SearchBarProps {
  placeholder?: string;
  // This describes a function that takes a string and returns nothing.
  // The param name is 'query' and the return type is 'void'.
  onSearch: (query: string) => void;
}

// The default value of 'placeholder' is "Search..."
function SearchBar({ placeholder = "Search...", onSearch }: SearchBarProps) {
  return (
    <input
      placeholder={placeholder}
      onChange={(e) => onSearch(e.target.value)}
    />
  );
}

export default SearchBar;