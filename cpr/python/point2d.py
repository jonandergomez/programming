import os
import sys
import math

class Point2d:
    '''
        Class for represeting points in the plane (two-dimensional space)
    '''
    epsilon = 1.0e-5

    def __init__( self, x=0.0, y=0.0 ):
        self.x = float(x)
        self.y = float(y)
        #self.epsilon = 1.0e-5

    def distance( self, other=None ):
        if other is None: other = Point2d()

        dx = self.x - other.x
        dy = self.y - other.y

        return math.sqrt( dx*dx + dy*dy )

    
    def __lt__( self, other ):
        return self.y < other.y or (self.y == other.y and self.x <  other.x)

    def __le__( self, other ):
        return self.y < other.y or (self.y == other.y and self.x <= other.x)

    def __eq__( self, other ):
        return abs(self.y - other.y) < self.epsilon and abs(self.x - other.x) < self.epsilon

    def __ne__( self, other ):
        return abs(self.y - other.y) > self.epsilon or abs(self.x - other.x) >= self.epsilon

    def __ge__( self, other ):
        return self.y > other.y or (self.y == other.y and self.x >= other.x)

    def __gt__( self, other ):
        return self.y > other.y or (self.y == other.y and self.x >  other.x)
    
    def __str__( self ):
        return f"({self.x},{self.y})"



if __name__ == '__main__':
    
    p0 = Point2d()
    p1 = Point2d( x=1, y=2 )

    print( f"p0 {p0}" )
    print( f"p1 {p1}" )
    print( "%s and %s %s equal" % (p0,p1, "are" if p0==p1 else "are NOT" ) )
