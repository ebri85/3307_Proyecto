00001  IDENTIFICATION DIVISION. 
00002   PROGRAM-ID. CALCULOS. 
00003   AUTHOR. MARIO QUIROS. 
00004 * 
00005 *<-- Columna 7 con asterisco significa comentario 
00006 * 
00007 *	CURSO COMPILADORES                    esta es la columna 72--> *       
00008 *             
00009 *8901<-- Este es el margen A (columnas 8 a 11) 
00010 *	2<-- Aqui empieza el margen B (columna 12) 
00011 * 
00012  ENVIRONMENT DIVISION. 
 
00014  DATA DIVISION.                                                    
00015   WORKING-STORAGE SECTION.                                         
00016    77 NUMERO1    PIC 9(2) VALUE ZEROS.                                       
	 77 NUMERO2    PIC 9(2) VALUE ZEROS. 
00018    77 RESULTADO1 PIC 9(2)V9(2) VALUE ZEROS. 
00019    77 RESULTADO2 PIC 9(2)V9(2) VALUE ZEROS. 
00020    77 RESULTADO3 PIC 9(2)V9(2) VALUE ZEROS. 
00021    77 RESULTADO4 PIC S9(2)V9(2) VALUE ZEROS. 
00022    77 RESULTADO5 PIC 9(2)V9(2) VALUE ZEROS. 
 
00024  PROCEDURE DIVISION. 
00025   INICIO.                   
	   DISPLAY "PRIMER  NUMERO: " WITH NO ADVANCING.            
	   ACCEPT NUMERO1. 
00028      DISPLAY "SEGUNDO NUMERO: " WITH NO ADVANCING. 
00029      ACCEPT NUMERO2. 
00030      COMPUTE RESULTADO1 = NUMERO1 * NUMERO2. 
00031      COMPUTE RESULTADO2 = NUMERO1 / NUMERO2. 
00032      COMPUTE RESULTADO3 = NUMERO1 + NUMERO2. 
00033      COMPUTE RESULTADO4 = NUMERO1 - NUMERO2. 
00034      COMPUTE RESULTADO5 = NUMERO1 * NUMERO1 * (NUMERO2 * NUMERO2). 
00035      DISPLAY "MULTIPLICACION: ", RESULTADO1. 
00036      DISPLAY "DIVISION      : ", RESULTADO2. 
00037      DISPLAY "SUMA          : ", RESULTADO3. 
00038      DISPLAY "RESTA         : ", RESULTADO4. 
00039      DISPLAY "EXPRESION     : ", RESULTADO5. 
00040      STOP RUN. 
