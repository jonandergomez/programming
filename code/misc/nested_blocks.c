#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *arg[])
{
    int x = 10;

    {
        int x = 91;
        printf("%d\n", x);
    }
    printf("%d\n", x);

    return EXIT_SUCCESS;
}
