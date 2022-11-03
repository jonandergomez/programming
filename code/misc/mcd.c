#include <stdio.h>

int mcd(int a, int b)
{
    while (a != b && a != 0 && b != 0) {
        if (a > b) {
            a = a % b;
        } else {
            b = b % a;
        }
    }
    if (a != 0) return a;
    else if (b != 0) return b;
    else return 1;
}