/*
    Note: Up til now we have shown interfaces within the 
    component files with which they are being used, but 
    it is common to separate the interfaces into their own
    separate files and directories.

    Since an interface is not a component or class, we explicitly
    call out that it is a 'Type' export.
*/
interface Pokemon {
    id: number;
    name: string;
    sprite: string;     // This is a string for the pokemon's image URL
    types: string[];
}

export type {Pokemon};