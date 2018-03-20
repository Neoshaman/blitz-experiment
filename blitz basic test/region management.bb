Include "function library.bb"

Graphics3D 800,600,0,2
SetBuffer BackBuffer()
AntiAlias True
;------------------------------------------- scene
cam = CreateCamera ()
PositionEntity cam, 0,0,-5

l1 = CreateLight (1)
RotateEntity l1, 45,45,0

tex=LoadTexture( "testure.jpg" )

center = CreateSphere (3)
PositionEntity center, 0,0,0
ScaleEntity center, .1,.1,.1
EntityColor center, 255,0,0

;------------------------------------------- extra



;------------------------------------------- loop

While Not KeyDown(1)
	controlentity(cam)

	;refresh
	UpdateWorld 1
	RenderWorld 1

;***************************************************** drawing section
	;showjoystate(100)
	;Text 0,0, "x: "+EntityX(cam) +"  y: "+EntityY(cam) +"  z: "+EntityZ(cam)
	
	x = MouseX()
	y = MouseY()
	
	
	;regioncut(380,280, x,y, 3, 3)				; WH, posXY, division, depth
	
	regiondraw(380,280, x,y, 3, 410,310, 4, 1)	; WH, posXY, division, XY, depth, divide
	
	;regionlod(380,280, x,y, 3, 3)			; WH, posXY, division, depth
	
	;count discrete position to lod step
	;regionpaint(780,580, x,y, 10,10, 1)		; WH, posXY, division, XY, depth
	
	;posXY = curseur pos
	;WH = size of originlal region
	;divsion = subdivion of region
	;depth = number of iteration of subdivision
	;XY = region offset coordinate
	;divide = color managment
	
;*****************************************************

	VWait:Flip False
	
Wend
ClearWorld 