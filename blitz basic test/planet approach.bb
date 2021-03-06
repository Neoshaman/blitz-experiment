Graphics3D 1000,600,0,2
SetBuffer BackBuffer()

Const offsetx = 0
Const offsety = 0

Origin offsetx+GraphicsWidth()/2, offsety+GraphicsHeight()/2

;planet visibility
;- surface limit (min height or Radius)
;- max height (+radius)
;- max atmosphere (+radius)


radius# = 90
maxheight# = 20+radius
atmosphere# = 40+radius

planetx# = -30
planety# = -20; 100

;distance = 0 ;radius + distance to surface
shipx# = 200
shipy# = 250; -50

;-------------------------------------------
cam = CreateCamera ()
PositionEntity cam, 0,0,-25

l1 = CreateLight (1)
RotateEntity l1, 45,45,0

;center = CreateSphere (3)
;PositionEntity center, 0,0,0
;ScaleEntity center, 5,5,5
;EntityColor center, 255,0,0
;-------------------------------------------

While Not KeyDown(1)

	;refresh
	UpdateWorld 1
	RenderWorld 1

shipx = -offsetx + MouseX()-GraphicsWidth()/2
shipy = -offsety + MouseY()-GraphicsHeight()/2

;axis
Color 255,0,0:Line 0,-GraphicsHeight(),0, GraphicsHeight()
Color 0,255,0:Line -GraphicsWidth(),0,GraphicsWidth(),0

;ship position
Color 255,0,0: circle (shipx,shipy, 10)

;planet position
Color 255,255,0: circle(planetx, planety, radius*2)

;max height And atmosphere
Color 0,0,64: circle(planetx, planety, atmosphere*2)
Color 64,0,64: circle(planetx, planety, maxheight*2)

;planet/ship distance
Color 64,64,64: Line shipx, shipy,  planetx, planety

;data
psx# = planetx - shipx
psy# = planety - shipy

dist# = magnitude(psx, psy)

r2# = radius*radius
d2# = dist*dist

T2# = d2 - r2
T# = Sqr(T2)

max2# = maxheight*maxheight
ext2# = max2 - r2
ext# = Sqr(ext2)

at2# = atmosphere*atmosphere
exat2# = at2 - r2
exat# = Sqr(exat2)

a# =  r2/dist
h# =  Sqr(r2 - (r2*r2)/d2 )

ix2# = planetx - a * psx /dist
iy2# = planety - a * psy /dist

itx2# = ix2 - h * psy /dist
ity2# = iy2 + h * psx /dist

itx1# = ix2 + h * psy /dist
ity1# = iy2 - h * psx /dist


;tangent vis
circle (ix2 ,iy2 ,20)
circle (itx2, ity2,20)
circle (itx1, ity1,20)

Line shipx,		shipy,		itx2, ity2
Line shipx,		shipy,		itx1, ity1
Line planetx,	planety,	itx2, ity2
Line planetx,	planety,	itx1, ity1

;maxheight and atmosphere
;--------------------------------------------------------------------------------
norm1x# = normalizeX( (-shipx+itx2), (-shipy+ity2) )
norm1y# = normalizey( (-shipx+itx2), (-shipy+ity2) )

norm2x# = normalizeX( (-shipx+itx1), (-shipy+ity1) )
norm2y# = normalizey( (-shipx+itx1), (-shipy+ity1) )


next2x# = (ext*norm1x+itx2)
next2y# = (ext*norm1y+ity2)

next1x# = (ext*norm2x+itx1)
next1y# = (ext*norm2y+ity1)


nexat2x# = (exat*norm1x+itx2)
nexat2y# = (exat*norm1y+ity2)

nexat1x# = (exat*norm2x+itx1)
nexat1y# = (exat*norm2y+ity1)
;--------------------------------------------------------------------------------
Color 64,0,64
circle(next2x, next2y, 10)
circle(next1x, next1y, 10)

Line itx2, ity2, next2x, next2y
Line itx1, ity1, next1x, next1y

Color 0,0,64
circle( nexat2x, nexat2y, 10)
circle( nexat1x, nexat1y, 10)

Line next2x, next2y, nexat2x, nexat2y
Line next1x, next1y, nexat1x, nexat1y

;--------------------------------------------------------------------------------


;distance circle
;circle (shipx, shipy, dist*2)

;mid distance circle (tangent intersect?)
;Color 0,255,255: circle( (planetx+shipx)*.5, (planety+shipy)*.5,magnitude((shipx-planetx), (shipy-planety)))

;horizon size
horizon = magnitude((itx1-itx2),(ity1-ity2))
Text 0,0, horizon 


	VWait:Flip False	
Wend
ClearWorld 

;--------------------------------------------------------------------------------

Function magnitude#(x#,y#)
	Return Sqr ((x*x) + (y*y))
End Function

Function normalizeX#(x#,y#)
	Return x/magnitude(x,y)
End Function

Function normalizeY#(x#,y#)
	Return y/magnitude(x,y)
End Function

Function Circle(x#,y#, radius#)
	Oval x-radius*0.5, y-radius*0.5,  radius,radius,0
	Plot x,y
End Function

Function powerof2step(x)
	;which step you are on : int 1 =
	Return Floor (Log (x)/Log(2) )
	;height of that step, float h = pow(2,i)
End Function