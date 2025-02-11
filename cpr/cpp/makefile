CFLAGS=-g -Wall -std=c11 -O3 -I.
CPPFLAGS=-g -Wall -std=c++11 -O3 -I.

all: sorting.exe sorting_stl.exe check_gcd.exe check_pf.exe

check_gcd.exe: greatestCommonDivisor.o check_gcd.o
	g++ $(CPPFLAGS) -o $@ $+

check_pf.exe: primeFactorization.o check_pf.o
	g++ $(CPPFLAGS) -o $@ $+

clean:
	rm -f *.exe *.o *.a *.so
	rm -rf *.exe.dSYM


%.o: %.c
	gcc $(CFLAGS) -c $?

%.exe: %.c
	gcc $(CFLAGS) -o $@ $?

%.o: %.cpp
	g++ $(CPPFLAGS) -c $?

%.exe: %.cpp
	g++ $(CPPFLAGS) -o $@ $?
