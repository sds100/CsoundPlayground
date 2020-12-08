<CsoundSynthesizer>
<CsOptions>
-odac -dm0 -+msg_color=0
</CsOptions>
; ==============================================
<CsInstruments>

sr	=	48000
ksmps	=	1
;nchnls	=	2
0dbfs	=	1

instr 1	
	aSin=poscil(0dbfs/4, 1000)
	out(aSin)

endin

</CsInstruments>
; ==============================================
<CsScore>
i 1 0 1


</CsScore>
</CsoundSynthesizer>

