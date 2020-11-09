       IDENTIFICATION DIVISION. 
        PROGRAM-ID. CALCULOS. 
        AUTHOR. MARIO QUIROS. 
      * 
      *<-- Columna 7 con asterisco significa comentario 
      * 
      *	CURSO COMPILADORES                    esta es la columna 72--> *       
      *             
      *8901<-- Este es el margen A (columnas 8 a 11) 
      *	2<-- Aqui empieza el margen B (columna 12) 
      * 
       ENVIRONMENT DIVISION. 
 
       DATA DIVISION.                                                    
        WORKING-STORAGE SECTION.                                         
         77 NUMERO1    PIC 9(2) VALUE ZEROS.                                       
77 NUMERO2    PIC 9(2) VALUE ZEROS. 
         77 RESULTADO1 PIC 9(2)V9(2) VALUE ZEROS. 
         77 RESULTADO2 PIC 9(2)V9(2) VALUE ZEROS. 
         77 RESULTADO3 PIC 9(2)V9(2) VALUE ZEROS. 
         77 RESULTADO4 PIC S9(2)V9(2) VALUE ZEROS. 
         77 RESULTADO5 PIC 9(2)V9(2) VALUE ZEROS. 
 
       PROCEDURE DIVISION. 
        INICIO.                   
	   DISPLAY "PRIMER  NUMERO: " WITH NO ADVANCING.            
	   ACCEPT NUMERO1. 
           DISPLAY "SEGUNDO NUMERO: " WITH NO ADVANCING. 
           ACCEPT NUMERO2. 
           COMPUTE RESULTADO1 = NUMERO1 * NUMERO2. 
           COMPUTE RESULTADO2 = NUMERO1 / NUMERO2. 
           COMPUTE RESULTADO3 = NUMERO1 + NUMERO2. 
           COMPUTE RESULTADO4 = NUMERO1 - NUMERO2. 
           COMPUTE RESULTADO5 = NUMERO1 * NUMERO1 * (NUMERO2 * NUMERO2). 
           DISPLAY "MULTIPLICACION: ", RESULTADO1. 
           DISPLAY "DIVISION      : ", RESULTADO2. 
           DISPLAY "SUMA          : ", RESULTADO3. 
           DISPLAY "RESTA         : ", RESULTADO4. 
           DISPLAY "EXPRESION     : ", RESULTADO5. 
           STOP RUN. 
