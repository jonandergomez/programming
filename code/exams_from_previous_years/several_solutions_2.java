// solutions to problems about files and exceptions of previous years

public void sumInt( String fileIn, String fileOut )
    throws FileNotFoundException
{
    Scanner input = new Scanner( new File( fileIn ) );
    PrintWriter output = new PrintWriter( new File( fileOut ) );

    int sum=0, value;
    while( input.hasNext() ) {

        try {
            value = input.nextInt();
            output.println(value);
            sum += value;
        }
        catch( InputMismatchException e )
        {
            String token = input.next();
            output.printf( "(Error: %s)\n", token );
        }
    }
    output.printf( "Sum: %s\n", sum );

    input.close();
    output.close();
}

public void findErrorsintATP( String fileIn, String fileOut )
    throws FileNotFoundException
{
    Scanner input = new Scanner( new File( fileIn ) );
    PrintWriter output = new PrintWriter( new File( fileOut ) );
    int lineNumber=0;
    while( input.hasNext() ) {
        String line = input.nextLine().trim();
        ++lineNumber;
        String [] tokens = null;

        if ( line.length() > 0 ) tokens = line.split( "([ \t])+" );
        try {
            if ( tokens == null || tokens.length != 4 ) {
                output.printf( "Error line %d: Unexpected number of columns.\n", lineNumber );
            } else {
                String name = tokens[0];
                int age = Integer.parseInt( tokens[1] );
                int points = Integer.parseInt( tokens[2] );
                int championships = Integer.parseInt( tokens[3] );

                if ( age < 0 || points < 0 || championships < 0 )
                    output.printf( "Error line %d: Negative value.\n", lineNumber );
            }
        }
        catch( NumberFormatException e ) {
            output.printf( "Error line %d: Invalid format for an integer.\n", lineNumber );
        }
    }
    input.close();
    output.close();
}

public File arrayToFile( int [] a )
{
    try {
        File f = new File( "ArrayElements.txt" );
        PrintWriter output = new PrintWriter( f ); // here an exception can be thrown
        for( int i=0; i < a.length; i++ ) {
            output.println( a[i] );
        }
        output.close();
    }
    catch( FileNotFoundException e )
    {
        System.err.printf( "File %s can not be opened!\n", "ArrayElements.txt" );
    }

    return f;
}


public StackIntLinked fileToStack( String filename )
{
    StackIntLinked stack = new StackIntLinked();

    Scanner input = null;

    try {
        input = new Scanner( new File( filename ) );

        while( input.hasNext() ) {

            try {
                int value = input.nextInt();
                stack.push( value );
            }
            catch( InputMismatchException e ) {
                input.next(); // ignore the invalid token
            }
        }
    }
    catch( FileNotFoundException e ) {
        System.err.println( "File not found!" );
    }
    finally {
        if ( input != null ) input.close();
    }

    return stack;
}

public static ListIntLinked copyAndSubstract( ListIntLinked l )
{
    if ( l == null || l.isEmpty() ) return null;

    ListIntLinked l2 = new ListIntLinked();

    l.begin();
    int minValue = l.get();
    while( l.next() ) minValue = Math.min( minValue, l.get() );

    for( boolean valid=l.begin(); valid; valid=l.next() ) {
        l2.append( l.get() - minValue );
    }

    return l2;
}

public static ListIntLinked onlyEvenNumbers( ListIntLinked l )
{
    if ( l == null || l.isEmpty() ) return null;

    ListIntLinked l2 = new ListIntLinked();

    for( boolean valid=l.begin(); valid; valid=l.next() ) {
        if ( l.get() % 2 == 0 ) l2.append( l.get() );
    }

    return l2;
}

public QueueIntLinked splitQueue()
{
    QueueIntLinked q2 = new QueueIntLinked();

    NodeInt node = this.first;
    for( int i=0; 2*i < this.size; i++ ) node=node.getNext();

    NodeInt previous = node.getPrevious();

    q2.first = node;
    q2.last = this.last;
    q2.size = this.size - i;

    this.last = previous;
    this.size = i;

    q2.first.setPrevious(null);
    this.last.setNext(null);

    return q2;
}
