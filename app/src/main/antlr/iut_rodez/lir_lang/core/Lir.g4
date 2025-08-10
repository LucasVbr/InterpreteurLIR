grammar Lir;

@header {
package iut_rodez.lir_lang.core;
}

prog: INTEGER EOF;

INTEGER: [0-9]+;
WS: [ \t\r\n]+ -> skip;