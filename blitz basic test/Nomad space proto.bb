Graphics3D 800,600,0,2
SetBuffer BackBuffer()

; planet data
	; Area = 4 * Pi * radius²
	; radius = (1/2)*Sqr(Area/Pi)
	; diameter = 2 * radius
	; volume = (4/3) * Pi * radius^3

;x,y, z = away

;galaxy (4096,512,4096) -> sector (8*8*8) -> star system

PlayerX = 0
PlayerY = 0
PlayerZ = 0

k = 1000
m = 1000*k
b = 1000*m;+/-2b is max
;set up

;reference frame:
; gui -> internal space -> 
; Close Local space (close  To 500m) -> far Local space (500m To beyond) -> 
; scale space rendering (galaxy, skybox, atmosphere) 1:6000 in kerbal


;camera far local space
	camfar = CreateCamera ()
	CameraProjMode camfar ,1;0= disable : 1= perspective, 2= orthographic
	
	CameraFogMode camfar , False
	CameraFogRange camfar , 0, 100
	CameraFogColor camfar , 255,255,255
	
	CameraViewport camfar , 0,0, GraphicsWidth(),GraphicsHeight()
	CameraClsMode camfar , True,True ;clear the: color, zbuffer 
	CameraClsColor camfar , 0,0,0
	
	CameraRange camfar , 5*k, 10*m
	CameraZoom camfar , 1
	
	PositionEntity camfar , 0,0,0


	;camera close local space
	camclose = CreateCamera ()
	CameraProjMode camclose ,1;0= disable : 1= perspective, 2= orthographic
	
	CameraFogMode camclose , False
	CameraFogRange camclose , 0, 100
	CameraFogColor camclose , 255,255,255
	
	CameraViewport camclose , 0,0, GraphicsWidth(),GraphicsHeight()
	CameraClsMode camclose , False,True  ;clear the: color, zbuffer 
	CameraClsColor camclose , 0,0,0
	
	CameraRange camclose , 0.1, 5*k
	CameraZoom camclose , 1
	
	PositionEntity camclose , 0,0,0
	
	
	
	;light
	AmbientLight 128,128,128
	light = CreateLight(1);1: directional, 2:spot, 3:point
	LightColor light,255,255,255
	
	LightConeAngles light, 60,90
	LightRange light,100
	
	RotateEntity light, 45,45,0
	
	
	
	;planet
	planet = CreateSphere (64)

	NameEntity planet, "big planet"
	EntityAutoFade planet,10*m,10*m
	EntityOrder planet,0; +:behind, -:above (all disable zbuffer)
	ScaleEntity planet, 100*k,100*k,100*k
	PositionEntity planet, 0,0,1*m

	EntityFX planet, 0 ;0:nothing,1:fullbright,2:vertex color,4:flatshad,8:disablefog,16:!backface,32:force alpha
	EntityColor planet, 255,064,064
	EntityAlpha planet, 1; 0-1 range , 1 = opaque
	EntityShininess planet,0; 0-1 range , 1 = shiny
	EntityBlend planet,1; 1:alpha , 2:mul , 3:add

	Print planet:WaitKey()

	;pertubation on big planet
	surf = GetSurface (planet,1)
	For i = 0 To CountVertices (surf)-1
		VertexCoords surf, i, VertexX (surf,i) * Rnd (0.8,1.1), VertexY (surf,i) * Rnd (0.8,1.1) , VertexZ (surf,i) * Rnd (0.9,1.2)
	Next
	UpdateNormals planet



	;planet2
	planet2 = CreateSphere (12)
	
	NameEntity planet2, "small planet"
	EntityAutoFade planet2,10*m,10*m
	EntityOrder planet2,0; +:behind, -:above (all disable zbuffer)
	ScaleEntity planet2, 1,1,1
	PositionEntity planet2, 1,0,6

	EntityFX planet2, 0 ;0:nothing,1:fullbright,2:vertex color,4:flatshad,8:disablefog,16:!backface,32:force alpha
	EntityColor planet2, 064,064,064
	EntityAlpha planet2, 1; 0-1 range , 1 = opaque
	EntityShininess planet2,0; 0-1 range , 1 = shiny
	EntityBlend planet2,1; 1:alpha , 2:mul , 3:add
		
	;pertubation on big planet
	surf = GetSurface (planet2,1)
	For i = 0 To CountVertices (surf)-1
		VertexCoords surf, i, VertexX (surf,i) * Rnd (0.8,1.2), VertexY (surf,i) * Rnd (0.8,1.2) , VertexZ (surf,i) * Rnd (0.8 ,1.2)
	Next
	UpdateNormals planet2
	
	
	
While Not KeyDown(1)

	;refresh
	UpdateWorld 1
	RenderWorld 1
	
	VWait:Flip False
	

	
Wend
ClearWorld 

Type celestials
	Field x
	Field y
	Field z
	;name, type, ...
End Type

Type player
	Field x
	Field y
	Field z
	Field health
	
	Field sector;currect, index by flat array index by 4096²x512x512 indices, might be empty or have a star system
	Field condition
	
	Field shiptype
	Field shipx
	Field shipy
	Field shipz
	Field shiphealth
	
	Field fuel ;hyper drive
	Field cash
	
	Field legalStatus
	Field rating; alignement
	Field level;
	
	
End Type


Function setCelestials ()
	;define name
	;define type (planet, asteroids, moon, star, space station, jump relay, etc ...)
	;set visual parameter
	;set landscape parameter
	
	
End Function