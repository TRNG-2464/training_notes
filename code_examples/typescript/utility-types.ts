interface User {
  id: number;
  name: string;
  email: string;
}

// ---------------------------------------------------------
// Partial<Type> -- all properties become optional
// Useful for something like a partial update payload
// ---------------------------------------------------------
type UserUpdate = Partial<User>;
let update: UserUpdate = { name: "Ada Lovelace" }; // OK -- other fields not required


// ---------------------------------------------------------
// Required<Type> -- all properties become required
// (Opposite of Partial -- useful if a base type has optional fields)
// ---------------------------------------------------------
interface Settings {
  theme?: string;
  fontSize?: number;
}
type FinalizedSettings = Required<Settings>;
// let incomplete: FinalizedSettings = { theme: "dark" }; // Error -- fontSize now required


// ---------------------------------------------------------
// Readonly<Type> -- all properties become read-only
// ---------------------------------------------------------
type LockedUser = Readonly<User>;
let lockedUser: LockedUser = { id: 1, name: "Ada", email: "ada@example.com" };
// lockedUser.name = "Ada Lovelace"; // Error -- all properties are readonly


// ---------------------------------------------------------
// Record<Keys, Type> -- builds an object type with specific keys,
// all mapped to the same value type
// ---------------------------------------------------------
type Role = "admin" | "editor" | "viewer";
type RolePermissions = Record<Role, boolean>;

let permissions: RolePermissions = {
  admin: true,
  editor: true,
  viewer: false,
};


// ---------------------------------------------------------
// Pick<Type, Keys> -- creates a type with only the specified keys
// ---------------------------------------------------------
type UserSummary = Pick<User, "id" | "name">;
let summary: UserSummary = { id: 1, name: "Ada" }; // "email" excluded


// ---------------------------------------------------------
// Omit<Type, Keys> -- creates a type with all keys except the specified ones
// ---------------------------------------------------------
type UserWithoutEmail = Omit<User, "email">;
let noEmail: UserWithoutEmail = { id: 1, name: "Ada" };


// ---------------------------------------------------------
// Exclude<UnionType, ExcludedMembers> -- removes members from a union
// ---------------------------------------------------------
type Status = "active" | "inactive" | "pending";
type ActiveOnlyStatus = Exclude<Status, "inactive" | "pending">; // "active"


// ---------------------------------------------------------
// Extract<UnionType, Members> -- keeps only the specified members
// ---------------------------------------------------------
type PendingOnlyStatus = Extract<Status, "pending" | "inactive">; // "inactive" | "pending"


// ---------------------------------------------------------
// ReturnType<FunctionType> -- extracts a function's return type
// ---------------------------------------------------------
function createUser() {
  return { id: 1, name: "Ada", email: "ada@example.com" };
}
type NewUser = ReturnType<typeof createUser>; // { id: number; name: string; email: string }


// ---------------------------------------------------------
// Parameters<FunctionType> -- extracts a function's parameter types
// ---------------------------------------------------------
function updateUser(id: number, changes: Partial<User>): void {}
type UpdateUserParams = Parameters<typeof updateUser>; // [number, Partial<User>]