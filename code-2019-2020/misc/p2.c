
int pow2( int a, int b )
{
    int p=1;
    for( int i=1; i <= b; i++ ) {
        p*=a;
    }
    return p;
}
