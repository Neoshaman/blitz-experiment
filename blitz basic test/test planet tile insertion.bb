Include "function library.bb"

BaseSetup3d()
tex= LoadTexture ("rp_alison_rigged_001_dif_gloss.tga")
cube = CreateCube()
EntityTexture cube, tex
ScaleEntity cube, 5,5,0.1

centerX = GraphicsWidth()/2
centerY = GraphicsHeight()/2

;C:\Program Files\Android\Android Studio
;C:\Users\sadeco\AppData\Local\Android\sdk

While Not KeyDown(1)
	
	
	
	;RenderWorld
	
	Cls
	
	Line centerX,CenterY, MouseX(),MouseY()
	
	CenteredCircle( centerX,centerY, 200 )
	squareVertex( centerX,centerY, 200 )

	


	VWait:Flip False

Wend
	
ClearWorld 
End












Function CenteredCircle( x,y, size )
	Oval x-(size/2),y-(size/2), size ,size , 0
	Plot x,y
End Function

Function squareVertex(x,y, size)

	Color 255,255,000

	size = size/2
	division = size/10
	
	For sx = 0 To 20
		Plot (x-size) + division*sx,(y-size)
		Plot (x-size) + division*sx,(y+size)

		Plot (x-size), (y-size)+ division*sx
		Plot (x+size), (y-size)+ division*sx

	Next
	
	Color 255,255,255
	
	;todo:
	;Replace Plot with circle
	;draw line
	;normalize projection
	;angle data structure + visualization
	;Vector2 circle;
	;	circle.x = square.x * Mathf.Sqrt(1f - square.y * square.y * 0.5f);
	;	circle.y = square.y * Mathf.Sqrt(1f - square.x * square.x * 0.5f);
	
End Function