package com.revature.streams.basics;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/*
 * Stream - A sequence of data in Java. Streams allow you to perform operations on some data (generally a collection)
 * 		to produce a new set of data without effecting the original.
 *
 * A 'Stream Pipeline' consists of the operations which run on a stream to produce a result.
 * 		Note: in a Stream pipeline, once an operation is conducted on a stream, the data will not return
 * 			to that previous state. (i.e. you cannot 'go backwards' in a Stream)
 *
 * Streams can either be:
 * 	'finite' (the stream has a limit, meaning they will inevitably 'finish')
 * 	'infinite' (the stream does not have a definitive 'end' and can potentially go on forever)
 *
 * A Stream consists of:
 * 	1. a 'Source' - origin of the data for the stream
 * 	2. zero or more 'Intermediate Operations' - An operations which transforms the stream into another one.
 * 		Note: Streams are 'lazily evaluated', meaning that the intermediate operations will not run until the
 * 			terminal operation runs.
 * 	3. zero or one 'Terminal Operation' - An operation which produces a result. Once a terminal operation completes
 * 		the Stream is no longer valid.
 */
public class StreamBasics {

    public static void printStreamData(Stream s, String message) {
        System.out.println(message);
        s.forEach((x) -> System.out.print(x + " | "));
//        s.forEach(System.out::print);
        System.out.println();
    }

    public static void main(String[] args) {
        /*
         * Finite Stream Creation:
         * 		Stream.empty() - creates a stream with zero elements
         * 		Stream.of(varargs) - creates a stream of the supplied arguments
         * 		collection.stream() - creates a Stream from a Collection
         */
        Stream<String> empty = Stream.empty();	// creates an empty stream
        printStreamData(empty, "\t:::Empty Stream:::");

        Stream<String> singleElement = Stream.of("Hello"); // creates a stream of size 1
        printStreamData(singleElement, "\t:::SingleElement Stream:::");

        Stream<String> fromArray = Stream.of("ABC","DEF","GHI"); // creates a stream from an array (3 elements)
        printStreamData(fromArray, "\t:::FromArray Stream:::");

        List<String> list = List.of("a","bc","def");
        Stream<String> fromList = list.stream(); // creates a stream from a collection (List)
        printStreamData(fromList, "\t:::FromList Stream:::");

        Set<String> set = Set.of("a","b","c");
        Stream<String> fromSet = set.stream(); // creates a stream from a collection (Set)
        printStreamData(fromSet, "\t:::FromSet Stream:::");

        /*
         * Infinite Stream Creation:
         * 		Stream.generate(supplier) - creates a stream by calling the supplier upon each request
         * 		Stream.iterate(seed, unaryOperator) - creates a stream using the seed value as the first element
         * 											then each subsequent element is generated using the provided
         * 											implementation of the UnaryOperator Interface
         */
        Stream<Double> randNums = Stream.generate(Math::random);
        // Alternatively: Stream<Double> randNums = Stream.generate(() -> Math.random());

        Stream<Integer> evenNums = Stream.iterate(0, (n) -> { return n + 2; } );
        // Alternatively: Stream<Integer> evenNums = Stream.iterate(0, n -> n + 2 );

        int x = 15;
        System.out.printf("Printing %d elements from an Infinite Stream%n", x);
        evenNums.filter(n -> n % 4 == 0).limit(x).forEach( (s) -> {
            System.out.print(s + " | ");
        });

    }


}
