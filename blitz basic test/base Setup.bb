Include "function library.bb"

.setup

Graphics3D 800,600,16,2
SetBuffer BackBuffer()

;-------------------------------------------
cam = CreateCamera ()
PositionEntity cam, 0,0,-5

l1 = CreateLight (1)
RotateEntity l1, 45,45,0

tex=LoadTexture( "testure.jpg" )

plane = CreatePlane ()
PositionEntity plane, 0,-8,0
EntityColor plane, 64,32,16
EntityTexture plane,tex
;-------------------------------------------
