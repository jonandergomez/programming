#include <iostream>

void swap(int & a, int & b)
{
  int temp = a;
  a = b;
  b = temp;
}

int main(int argc, char * args[])
{
  int a = 5, b = 2;
  std::cout << "BEFORE: a = " << a << "  b = " << b << std::endl;
  swap(a, b);
  std::cout << "AFTER.: a = " << a << "  b = " << b << std::endl;

  return EXIT_SUCCESS;
}
