Graphics 800,600,0,2
HidePointer

;area position
rx = 300
ry = 100
;area size
rw = 400
rh = 400
;cursor size
sx = 10
sy = 10
;subdivision
hx = 100
hy = 100

;LoadFont 
;SetFont 

While Not KeyHit(1)

Cls



x = MouseX()
y = MouseY()

;If x > rx+rw -sx Then x = rx+rw-sx
;If y > ry+rh -sy Then y = ry+rh-sy

;If x > rx+rw-1 Then x = rx+rw-1
;If y > ry+rh-1 Then y = ry+rh-1
;If x < rx Then x = rx
;If y < ry Then y = ry


Color 255,000,000
Plot x,y




ox=x-rx
oy=y-ry
offsetx = ox/hx
offsety = oy/hy


;fill area
ex = offsetx + (rw/hx) ;end area position hashed
ey = offsety + (rh/hy)

Color 64,64,64
;Rect rx , ry , hx , hy , 0 ; test hash
;Rect rx+rw -hx, ry+rh -hy, hx , hy , 0

px = -rx + (offsetx *hx) ; position 
py = -ry + (offsety *hy)

Color 0, 64, 0
;Rect ex + offsetx *hx, ey + offsety *hy, hx, hy, 0
;Rect px, py, hx, hy, 0

For i=offsetx To ex;-1
	For j=offsety To ey;-1
		tx = (i - offsetx) *hx + rx + offsetx *hx;x ;rx;+ (x Mod hx); + rx;(ox- ( offsetx*hx)) + rx
		ty = (j - offsety) *hy + ry + offsety *hy; y ;ry;+ (y Mod hy); + ry;(oy- ( offsety*hy)) + ry

		Color i *hx, j *hy, 0
		
		Rect tx, ty, hx,hy,0
		;Rect tx, ty, hx, hy,1
		;Text tx,ty, i *hx 
		;Text tx,ty, j *hy
	Next
Next

Color 255,255,255
Rect x,y,rw,rh,0 ;floating windows
;Rect x-5,y-5, sx,sy,0;gated cursor
Rect x-5,y-5, sx,sy,0;cursor

Color 255,0,0
Rect rx,ry,rw,rh,0 ;area


;loop partition
Color 0,255,255
Plot (x Mod hx) + rx , (y Mod hy) + ry ;cyan point is modulo 
Color 255,255,255
Plot Abs((ox- ( offsetx*hx)) + rx ), Abs( ( oy- (offsety*hy)) + ry); only work with positive unlike modulo

Text 0,0, ((x Mod hx) ) + " : " +  ((y Mod hy) ) + " / " + (ox- ( offsetx*hx)) + " : " + (oy- (offsety*hy)) + " / " + ox + " : " + oy

;hash partition
Color 128,128,128
Rect rx + offsetx *hx, ry + offsety *hy, hx,hy,0

;seeded population
Color 0,255,0
SeedRnd (rx+offsetx *hx)*1000 + offsety *hy ; seed by position
iteration = Rnd(1,3)
For i = 1 To iteration
	Color Rnd(128,200),Rnd(128,200),Rnd(128,200)
	Rect  Rnd(rx + offsetx *hx, rx + offsetx *hx + hx )-2, Rnd(ry + offsety *hy, ry + offsety *hy + hy )-2, 4,4,1 ;seeded random position
Next



Flip

Wend
