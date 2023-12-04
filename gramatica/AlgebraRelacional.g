grammar AlgebraRelacional;

st : ( asg | con );
asg : rel ASSIGN con;
con : ( bin | select | proy | renom1 | renom2 | renom3 | join | groupby | orderby | agregation | division | reunatural | reuizq | reuder | reuext);

//Operadores
bin : rel ('UNION'|'union') rel
| rel ('INTER'|'inter') rel
| rel ('DIFERENCIA'|'diferencia') rel
| rel ('CRUZ'|'cruz') rel;

select : ('seleccionar'|'SELECCIONAR')'('conds1')''('rel')';
proy : ('proyectar'|'PROYECTAR')'('atts')''('rel')';
renom1 : ('renombrar'|'RENOMBRAR') rel '('atts')''('rel')';
renom2 : ('renombrar'|'RENOMBRAR') rel '('rel')';
renom3 : ('renombrar'|'RENOMBRAR')'('atts')''('rel')';
join : '('rel')'('reunion'|'REUNION')'('conds2')''('rel')';
groupby	: '(' atts')' ('agrupar'|'AGRUPAR') fagregas '('rel')';
orderby	: ('ordenar' | 'ORDENAR') orders '('rel')' ;
agregation : fagrega ( att '('rel')') ;
division: '('att ',' att2 ')''('rel')'('division'|'DIVISION')'('att')''('rel')';
reunatural: rel ('REUNION_NATURAL'|'reunion_natural')'('conds2')' rel;
reuizq: rel ('reunion_ext_izq'|'REUNION_EXT_IZQ')'('conds2')' rel;
reuder: rel ('reunion_ext_der'|'REUNION_EXT_DER')'('conds2')' rel;
reuext: rel ('reunion_ext_full'|'REUNION_EXT_FULL')'('conds2')' rel;

//condicioens
atts : att (',' att)*;
conds1 : cond1 (( 'and' | 'or' | 'AND' | 'OR' ) cond1)*;
conds2 : cond2 (( 'and' | 'or' | 'AND' | 'OR' ) cond2)*;
cond1 : ( con1| con11 );
cond2 : ( con2| con3 );
orders	: att '('order')' (',' att '('order')')*  ;
order	: ( 'asc' | 'ASC' | 'desc' | 'DESC' ) ;
fagregas: fagrega '('att')' (',' fagrega '('att')')* ;
fagrega	: ('sum' | 'SUM' | 'avg' | 'AVG' | 'count' | 'COUNT' | 'min' | 'MIN' | 'max' | 'MAX' );
con1 : att ( '=' |'<' | '<=' | '>' | '>= '| '!=' ) cons;
con11 : att ( '=' |'<' | '<=' | '>' | '>= '| '!=' ) att;
con2 : rel'.'att ( '=' |'<' | '<=' | '>' | '>= '| '!=' ) rel'.'att;
con3 : rel'.'att ( '=' |'<' | '<=' | '>' | '>= '| '!=' ) cons;
cons : ( num | cad );
num : ('-')?(NUM)+('.'(NUM)+)?;
cad : '\u0027'CAD'\u0027';
att : CAD;
att2 : CAD;
rel : CAD;
NUM : ('0'..'9')+;
CAD : (('a'..'z')|('A'..'Z')|('0'..'9')|'_')+;
WS : ( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
ASSIGN : ' := ';
