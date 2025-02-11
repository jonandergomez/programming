#include <cstdio>

int gcd(int a, int b, int *x, int *y);

using namespace std;

int main(int argc, char * argv[])
{
    int x, y;
    // int a = 34398, b = 2132;
    int a = 17, b = 14;
    // int a = 4, b = 6;

    int g = gcd(a, b, &x, &y);

    printf("\n\n");
    printf("%d * %d + %d * %d = %d\n", a, x, b, y, g);

    return 0;
}