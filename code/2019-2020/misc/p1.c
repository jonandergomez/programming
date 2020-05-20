
int pow1( int a, int b )
{
    int p=1;
    int i=1;
    while( i <= b ) {
        p*=a;
        i++;
    }
    return p;
}
