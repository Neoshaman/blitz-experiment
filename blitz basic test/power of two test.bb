Graphics 800,600,0,2
Origin 400,300

For i = 2 To 2^4
	
	ip = 2^(i-1)
	
	sn	= -2	* ip 
	se	= 2^i	- ip
	sh 	= 		  ip
			
	Rect se,se,		se,se,0		;diagonal low right
	Rect 0,se,		se,se,0		;low low right 
	Rect se,0,		se,se,0		;low right right 
	
	Rect sn,sn,		sh,sh,0		;diagonal high left
	Rect -sh,sn,	sh,sh,0		;high high left
	Rect sn,-sh,	sh,sh,0		;high left left 
	
	Rect se,sn,		sh,sh,0		;diagonal high right
	Rect 0,sn,		sh,sh,0		;high high right
	Rect sh,-se,	se,se,0		;high right right
	
	Rect sn,se,		se,se,0		;diagonal low left
	Rect -sh,se,	se,se,0		;low low left
	Rect sn,0,		se,se,0		;low left left

	Print chebyshev (se,se)
	
	Delay 500
	Flip
Next

WaitKey()
;------------------------------------------------------


Function Chebyshev (x1,y1, x2=0, y2=0)
	Return max( Abs(x1-x2), Abs(y1-y2) )
End Function


Function max (a,b)

	If a > b Then
		Return a
	Else 
		Return b
	EndIf 
	
End Function