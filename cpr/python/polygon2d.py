import os
import sys
import math
import numpy
from matplotlib import pyplot
from matplotlib.animation import FuncAnimation

from point2d import Point2d

def signed_area( A, B, C ):
    '''
        Assumes the three points are objects of the class Point2d
    '''
    a = 0.0
    a = a + A.x * B.y - A.y * B.x
    a = a + B.x * C.y - B.y * C.x
    a = a + C.x * A.y - C.y * A.x
    return a / 2.0

def area( A, B, C ):
    '''
        Assumes the three points are objects of the class Point2d
    '''
    return abs(signed_area(A,B,C))


def counter_clockwise( A, B, C ):
    '''
        Assumes the three points are objects of the class Point2d
    '''
    return signed_area(A,B,C) > Point2d.epsilon

def clockwise( A, B, C ):
    '''
        Assumes the three points are objects of the class Point2d
    '''
    return signed_area(A,B,C) < -Point2d.epsilon

def collinear( A, B, C ):
    '''
        Assumes the three points are objects of the class Point2d
    '''
    return abs(signed_area(A,B,C)) <= Point2d.epsilon

def point_inside( A, B, C, p ):
    return not (clockwise(A,B,p) or clockwise(B,C,p) or clockwise(C,A,p))
    

def sort_by_smaller_angle( points, reference=None ):
    '''
        Assumes that points[0] is the reference, then from position 1 to the end the points
        are going to be sorted according to the angle they form with respecte points[0].
    '''
    if reference is None:
        reference = points[0]
        init=1
    else:
        init=0
    #
    l=len(points)
    for i in range(init,l):
        j=i
        p = points[i]
        while j > 0 and compare_to( reference, p, points[j-1] ) < 0:
            points[j] = points[j-1]
            j-=1 
        points[j] = p


def compare_to( reference, p1, p2 ):
    #
    if collinear( reference, p1, p2 ):
        return -1 if reference.distance(p1) <= reference.distance(p2) else 1
    #
    return -1 if counter_clockwise(reference,p1,p2) else 1



class Polygon2d:
    '''
        Class for represeting polygons in the plane (two-dimensional space)
    '''

    def __init__( self, xy=None ):
        self.points=[]
        if xy is not None:
            if type(xy) == list:
                for p in xy: self.points.append( Point2d( p[0], p[1] ) )
            elif type(xy) == numpy.ndarray:
                if xy.shape[1] != 2:
                    raise Exception( 'FATAL ERROR: unexpected shape of the numpy array xy: %s ' % str(xy.shape) )
                for i in range(xy.shape[0]):
                    self.points.append( Point2d( xy[i,0], xy[i,1] ) )
            else:
                raise Exception( 'FATAL ERROR: unexpected data type for parameter xy.' )

    def __len__(self): return len(self.points)

    def area(self):
        a = 0.0
        l = len(self.points)
        for i in range(l):
            j = (i+1) % l
            a = a + self.points[i].x * self.points[j].y \
                  - self.points[i].y * self.points[j].x
                
        return a / 2.0


    def add(self, x, y ): self.points.append( Point2d( x, y ) )
    def append(self, p ): self.points.append( p )

    def remove_last(self): del self.points[-1]

    def point(self, i ): return self.points[i]

    def to_list(self):
        return [ [p.x,p.y] for p in self.points ]

    def possible_ear(self, left, current, right ):
        #
        if clockwise( left, current, right ): return False
        #
        for p in self.points:
            if  p != left and p != current and p != right:
                if point_inside( left, current, right, p ): return False
        #
        return True

    def triangulate(self):
        #
        l=len(self.points)
        left = [(i-1+l)%l for i in range(l)]
        right = [(i+1)%l for i in range(l)]
        #
        triangles=[]
        #
        current = len(self.points)-1
        while len(triangles) < len(self.points)-2:
            #
            current=right[current]
            #
            #print( current, len(triangles) ) # verbose
            #
            if self.possible_ear(   self.points[left[current]], self.points[current], self.points[right[current]] ):
                triangles.append( [ self.points[left[current]], self.points[current], self.points[right[current]] ] )
                left[ right[current] ] =  left[current]
                right[ left[current] ] = right[current]
            #
        return triangles


    def triangulate_2(self):
        #
        l=len(self.points)
        left = [(i-1+l)%l for i in range(l)]
        right = [(i+1)%l for i in range(l)]
        #
        triangles=[]
        #
        center = Point2d( sum( [p.x for p in self.points] )/len(self.points), sum( [p.y for p in self.points] )/len(self.points) )
        current = len(self.points)-1
        while len(triangles) < len(self.points)-2:
            #
            max_dist = 0
            next_point = c = right[current]
            c = right[c]
            counter=len(self.points)
            while c != next_point and counter > 0:
                counter-=1
                d = center.distance( self.points[c] )
                if d > max_dist and self.possible_ear( self.points[left[c]], self.points[c], self.points[right[c]] ):
                    max_dist = d
                    next_point = c
                c = right[c]
            current = next_point
            #
            if self.possible_ear(   self.points[left[current]], self.points[current], self.points[right[current]] ):
                triangles.append( [ self.points[left[current]], self.points[current], self.points[right[current]] ] )
                left[ right[current] ] =  left[current]
                right[ left[current] ] = right[current]
            #
        return triangles



def convex_hull(_points_=None):
    '''
        points: list of points from which to compute the convex hull

        returns an object of the class Polygon2d
    '''
    # 
    # makes a copy in order to maintain untouched the list of points provided as parameter
    if type(_points_) == numpy.ndarray :
        points = [Point2d(p[0],p[1]) for p in _points_]
    else:
        points = [Point2d(p.x,p.y) for p in _points_]
    #
    # sorts the points
    points.sort()
    #
    # removes duplicates
    _points = [points[0]]
    for p in points:
        if p != _points[-1] : _points.append(p)
    points = _points
    #
    sort_by_smaller_angle( points )
    #
    pol = Polygon2d()
    pol.append( points[0] )
    pol.append( points[1] )
    i=2
    while i < len(points):
    # 
        if counter_clockwise( pol.point(-2), pol.point(-1), points[i] ):
            pol.append( points[i] )
            i+=1
        else:
            pol.remove_last()
    #
    return pol

class AnimatedConvexHull(object):
    #
    def __init__(self, _points_ ):
        #
        self.figure = pyplot.figure( figsize=(10,8) )
        self.axes = self.figure.add_axes( [0.0,0.0,1.0,1.0], animated=True )
        #
        # 
        # makes a copy in order to maintain untouched the list of points provided as parameter
        if type(_points_) == numpy.ndarray :
            self.points = [Point2d(p[0],p[1]) for p in _points_]
        else:
            self.points = [Point2d(p.x,p.y) for p in _points_]
        #
        # sorts the points
        self.points.sort()
        #
        # removes duplicates
        _points = [self.points[0]]
        for p in self.points:
            if p != _points[-1] : _points.append(p)
        self.points = _points
        #
        sort_by_smaller_angle( self.points )
        #
        _line_points_ = self.points.copy()
        _line_points_.append( self.points[0] )
        self.plot__line,   = self.axes.plot(    [p.x for p in _line_points_ ], [p.y for p in _line_points_ ], c='orange', alpha=0.4 )
        self.plot_points   = self.axes.scatter( [p.x for p in  self.points  ], [p.y for p in  self.points  ], s=50, marker='s', c='orange', alpha=0.9 )
        #
        self.plot_polygon, = self.axes.plot(    [], [], marker='o', c='blue', alpha=0.7 )
        self.plot_current  = self.axes.scatter( [], [], s=80, marker='^', c='red', alpha=1.0 )

    def reset( self ):
        #
        self.pol = Polygon2d()
        self.pol.append( self.points[0] )
        self.pol.append( self.points[1] )
        #
        self.i=2 # this counter is independent of 'step' of the method __call__(), because it is necessary for this example
        #
        self.plot_polygon.set_data( [p.x for p in self.pol.points], [p.y for p in self.pol.points] )
        self.plot_current.set_offsets( [ self.points[self.i].x , self.points[self.i].y ] )
        #
        return self.plot__line, self.plot_points, self.plot_polygon, self.plot_current

    def __call__( self, step ):
        # 
        if step == 0 or self.i==0:

            return self.reset()

        elif self.i < len(self.points):
            # 
            if counter_clockwise( self.pol.point(-2), self.pol.point(-1), self.points[self.i] ):
                self.pol.append( self.points[self.i] )
                self.i+=1
            else:
                self.pol.remove_last()
            #
        elif self.i == len(self.points):
            self.pol.append( self.points[0] )
            self.i+=1
        elif self.i > len(self.points):
            self.i=0
        #
        self.plot_polygon.set_data( [p.x for p in self.pol.points], [p.y for p in self.pol.points] )
        if self.i < len(self.points):
            self.plot_current.set_offsets( [ self.points[self.i].x , self.points[self.i].y ] )
        else:
            self.plot_current.set_offsets( [ self.points[0].x , self.points[0].y ] )
        #
        return self.plot__line, self.plot_points, self.plot_polygon, self.plot_current
        #


if __name__ == '__main__':
    # 
    show_convex_hull = True
    show_triangulation = True
    #
    if show_convex_hull:
        points = numpy.random.randn( 20, 2 )
        pol = convex_hull( points )
        #
        pyplot.figure( figsize=(10,8) )
        pyplot.scatter( points[:,0], points[:,1], s=50, marker='s', c='red', alpha=0.2  )
        ch = pol.to_list()
        ch.append( ch[0] )
        ch = numpy.array(ch)
        pyplot.plot( ch[:,0], ch[:,1], c='blue', alpha=0.5  )
        pyplot.scatter( ch[:-1,0], ch[:-1,1], s=30, marker='o', c='blue', alpha=0.5  )
        pyplot.show()
        #
        #
        ach = AnimatedConvexHull( points )
        anim = FuncAnimation( ach.figure, ach, frames=numpy.arange(len(points)*10), init_func=ach.reset, interval=1000, blit=True )
        pyplot.show()
    #
    if show_triangulation:
        points = numpy.random.randn( 10, 2 )
        center=points.mean(axis=0)
        pol = Polygon2d( points )
        pol.points.sort()
        sort_by_smaller_angle( pol.points, reference=Point2d( center[0], center[1] ) )
        #
        x = [p.x for p in pol.points] ; x.append(x[0])
        y = [p.y for p in pol.points] ; y.append(y[0])
        #
        pyplot.figure( figsize=(10,8) )
        pyplot.fill( x, y, c='blue', alpha=0.3 )
        pyplot.plot( x, y, ms=7, marker='o', c='blue', alpha=1.0 )
        pyplot.show()
        #
        #triangles=pol.triangulate()
        triangles=pol.triangulate_2()
        #
        pyplot.figure( figsize=(10,8) )
        for tri in triangles:
            pyplot.fill( [p.x for p in tri], [p.y for p in tri], c='orange', alpha=0.3 )
        pyplot.plot( x, y, ms=7, marker='o', c='blue', alpha=1.0 )
        pyplot.show()
