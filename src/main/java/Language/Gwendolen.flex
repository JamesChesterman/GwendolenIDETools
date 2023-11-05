package Language;

import Language.psi.GwendolenTypes;
import com.intellij.lexer.FlexLexer;
import Grammar.GwendolenElementType;
import Language.Parser.psi.GwendolenTypes;
import com.intellij.psi.TokenType;


%%

%class GwendolenLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

GWENDOLEN="GWENDOLEN"
NAME=":name:"
CONST = [a-zA-Z0-9_]+
COMMENT = "/*" ([^*] | \*+ [^*/])*"*/"
LINE_COMMENT = "//" ~[\n]*
NEWLINE = (\r?\n)
WS = [ \t]+

BELIEFS = ":Initial Beliefs:"

//When in INITIAL_BELIEFS mode
BELIEFRULES=":Reasoning Rules:"
GOAL_IB=":Initial Goals:"
IB_COMMENT="/*" ([^*] | \*+ [^*/])*"*/"
IB_LINE_COMMENT="//" ~[\n]*
IB_NEWLINE=(\r?\n)
IB_WS=[ \t]+
BELIEF_BLOCK=([a-zA-Z0-9] |"_"|"("|")"|","|"."|" "|"\t"|"\n"|"\r"|"-"|"["|"]"|"|"|"\"")+

//When in REASONING_RULES mode
GOAL_RR=":Initial Goals:"
RR_COMMENT="/*" ([^*] | \*+ [^*/])*"*/"
RR_LINE_COMMENT="//" ~[\n]*
RR_NEWLINE=(\r?\n)
RR_WS=[ \t]+
RR_BLOCK=([a-zA-Z0-9] |"_"|"("|")"|","|"."|" "|"\t"|"\n"|"\r"|"-"|"["|"]"|"|"|"\"")+

//When in GOALS mode
PLANS=":Plans:"
GL_COMMENT="/*" ([^*] | \*+ [^*/])*"*/"
GL_LINE_COMMENT="//" ~[\n]*
GL_NEWLINE=(\r?\n)
GL_WS=[ \t]+
GL_ACHIEVEGOAL="achieve"
GL_PERFORMGOAL="perform"
GL_SQOPEN="["
GL_SQCLOSE="]"
GOAL_BLOCK=([a-zA-Z0-9] |"_"|"("|")"|","|"."|" ")+

//When in PLANS mode
NAME_PM=":name:"
PL_COMMENT="/*" ([^*] | \*+ [^*/])*"*/"
PL_LINE_COMMENT="//" ~[\n]*
PL_NEWLINE=(\r?\n)
PL_WS=[ \t]+
SEND=".send"
RECEIVED=".received"
BELIEVE=("B"|".B")
GOAL=("G"|".G")
SENT=".sent"
LOCK=".lock"

PL_ACHIEVEGOAL="achieve"
PL_PERFORMGOAL="perform"
PL_SQOPEN="["
PL_SQCLOSE="]"
PL_BAR="|"
NOT="~"
COLON=":"
CURLYOPEN="{"
CURLYCLOSE="}"
COMMA=","
SEMI=";"

TELL=":tell"
SHRIEK="!"
OPEN="("
CLOSE=")"
MULT="*"
PLUS="+"
MINUS="-"
LESS="<"
EQUAL="=="
IDPUNCT="."

RULEARROW="<-"

TRUE="True"
PL_CONST=[a-z]([a-zA-Z0-9]|"_"|".")*
PL_VAR=([A-Z]|"_")([a-zA-Z0-9]|"_")*
NUMBER=([0-9])([0-9]|".")*

QUOTED_STRING="\"" [^\"]* "\"" | "'" [^']* "'"



%state INITIAL_BELIEFS
%state REASONING_RULES
%state GOALS
%state PLANS_MODE

%{
    public int plain_nesting = 0;
    public int sq_nesting = 0;
    public int curly_nesting = 0;
%}

%%

<YYINITIAL> {GWENDOLEN}             {yybegin(YYINITIAL); return GwendolenTypes.GWENDOLEN; }
<YYINITIAL> {NAME}                  {yybegin(YYINITIAL); return GwendolenTypes.NAME; }
<YYINITIAL> {CONST}                 {yybegin(YYINITAL); return GwendolenTypes.CONST; }
<YYINITIAL> {COMMENT}               {yybegin(YYINITIAL); return GwendolenTypes.COMMENT; }
<YYINITIAL> {LINE_COMMENT}          {yybegin(YYINITIAL); return GwendolenTypes.}
<YYINITIAL> {NEWLINE}               {yybegin(YYINITIAL); return GwendolenTypes.}
<YYINITIAL> {WS}                    {yybegin(YYINITIAL); return GwendolenTypes.}

<YYINITIAL> {BELIEFS}               {yybegin(INITIAL_BELIEFS); return GwendolenTypes.BELIEFS; }

<INITIAL_BELIEFS> {BELIEFRULES}     {yybegin(REASONING_RULES); return GwendolenTypes.BELIEFRULES; }
<INITIAL_BELIEFS> {GOAL_IB}         {yybegin(GOALS); return GwendolenTypes.GOAL_IB; }
<INITIAL_BELIEFS> {IB_COMMENT}      {yybegin(INITIAL_BELIEFS); return GwendolenTypes.}
<INITIAL_BELIEFS> {IB_LINE_COMMENT} {yybegin(INITIAL_BELIEFS); return GwendolenTypes.; }
<INITIAL_BELIEFS> {IB_NEWLINE}      {yybegin(INITIAL_BELIEFS); return GwendolenTypes.; }
<INITIAL_BELIEFS> {IB_WS}           {yybegin(INITIAL_BELIEFS); return GwendolenTypes.; }
<INITIAL_BELIEFS> {BELIEF_BLOCK}    {yybegin(INITIAL_BELIEFS); return GwendolenTypes.BELIEF_BLOCK; }

<REASONING_RULES> {GOAL_RR}         {yybegin(GOALS); return GwendolenTypes.GOAL_RR; }
<REASONING_RULES> {RR_COMMENT}      {yybegin(REASONING_RULES); return GwendolenTypes.;}
<REASONING_RULES> {RR_LINE_COMMENT} {yybegin(REASONING_RULES); return GwendolenTypes.;}
<REASONING_RULES> {RR_NEWLINE}      {yybegin(REASONING_RULES); return GwendolenTypes.RR_NEWLINE;}
<REASONING_RULES> {RR_WS}           {yybegin(REASONING_RULES); return GwendolenTypes.;}
<REASONING_RULES> {RR_BLOCK}        {yybegin(REASONING_RULES); return GwendolenTypes.RR_BLOCK;}

<GOALS> {PLANS}                     {yybegin(PLANS_MODE); return GwendolenTypes.PLANS;}
<GOALS> {GL_COMMENT}                {yybegin(GOALS); return GwendolenTypes.;}
<GOALS> {GL_LINE_COMMENT}           {yybegin(GOALS); return GwendolenTypes.;}
<GOALS> {GL_NEWLINE}                {yybegin(GOALS); return GwendolenTypes.;}
<GOALS> {GL_WS}                     {yybegin(GOALS); return GwendolenTypes.;}
<GOALS> {GL_ACHIEVEGOAL}            {
          if(sq_nesting > 0){
              yybegin(GOALS);
              return GwendolenTypes.GL_ACHIEVEGOAL;
          }
}
<GOALS> {GL_PERFORMGOAL}            {
          if(sq_nesting > 0){
              yybegin(GOALS);
              return GwendolenTypes.GL_PERFORMGOAL;
          }
}
<GOALS> {GL_SQOPEN}                 {
          sq_nesting++;
          return GwendolenTypes.GL_SQOPEN;
}
<GOALS> {GL_SQCLOSE}                {
          sq_nesting--;
          return GwendolenTypes.GL_SQCLOSE;
}
<GOALS> {GOAL_BLOCK}                {yybegin(GOALS); return GwendolenTypes.GOAL_BLOCK;}

<PLANS_MODE> {NAME_PM}              {yybegin(YYINITIAL); return GwendolenTypes.NAME_PM;}
<PLANS_MODE> {PL_COMMENT}           {yybegin(PLANS_MODE); return GwendolenTypes.;}
<PLANS_MODE> {PL_LINE_COMMENT}      {yybegin(PLANS_MODE); return GwendolenTypes.;}
<PLANS_MODE> {PL_NEWLINE}           {yybegin(PLANS_MODE); return GwendolenTypes.;}
<PLANS_MODE> {PL_WS}                {yybegin(PLANS_MODE); return GwendolenTypes.;}
<PLANS_MODE> {SEND}                 {yybegin(PLANS_MODE); return GwendolenTypes.SEND;}
<PLANS_MODE> {RECEIVED}             {yybegin(PLANS_MODE); return GwendolenTypes.RECEIVED; }
<PLANS_MODE> {BELIEVE}              {
            if(curly_nesting > 0){
                yybegin(PLANS_MODE);
                return GwendolenTypes.BELIEVE;
            }
}
<PLANS_MODE> {GOAL}                 {
            if(curly_nesting > 0){
                yybegin(PLANS_MODE);
                return GwendolenTypes.GOAL;
            }
}
<PLANS_MODE> {SENT}                 {yybegin(PLANS_MODE); return GwendolenTypes.SENT; }
<PLANS_MODE> {LOCK}                 {yybegin(PLANS_MODE); return GwendolenTypes.LOCK; }
<PLANS_MODE> {PL_ACHIEVEGOAL}       {yybegin(PLANS_MODE); return GwendolenTypes.PL_ACHIEVEGOAL; }
<PLANS_MODE> {PL_PERFORMGOAL}       {yybegin(PLANS_MODE); return GwendolenTypes.PL_PERFORMGOAL; }
<PLANS_MODE> {PL_SQOPEN}            {yybegin(PLANS_MODE); return GwendolenTypes.PL_SQOPEN; }
<PLANS_MODE> {PL_SQCLOSE}           {yybegin(PLANS_MODE); return GwendolenTypes.PL_SQCLOSE; }
<PLANS_MODE> {PL_BAR}               {yybegin(PLANS_MODE); return GwendolenTypes.PL_BAR; }
<PLANS_MODE> {NOT}                  {yybegin(PLANS_MODE); return GwendolenTypes.NOT;}
<PLANS_MODE> {COLON}                {yybegin(PLANS_MODE); return GwendolenTypes.COLON; }
<PLANS_MODE> {CURLYOPEN}            {
            curly_nesting++;
            yybegin(PLANS_MODE);
            return GwendolenTypes.CURLYOPEN;
}
<PLANS_MODE> {CURLYCLOSE}           {
          curly_nesting--;
          yybegin(PLANS_MODE);
          return GwendolenTypes.CURLYCLOSE;
}
<PLANS_MODE> {COMMA}                {yybegin(PLANS_MODE); return GwendolenTypes.COMMA;}
<PLANS_MODE> {SEMI}                 {yybegin(PLANS_MODE); return GwendolenTypes.SEMI;}
<PLANS_MODE> {TELL}                 {yybegin(PLANS_MODE); return GwendolenTypes.TELL;}
<PLANS_MODE> {SHRIEK}               {yybegin(PLANS_MODE); return GwendolenTypes.SHRIEK;}
<PLANS_MODE> {OPEN}                 {
            plain_nesting++;
            yybegin(PLANS_MODE);
            return GwendolenTypes.OPEN;
}
<PLANS_MODE> {CLOSE}                {
            plain_nesting--;
            yybegin(PLANS_MODE);
            return GwendolenTypes.CLOSE;
}
<PLANS_MODE> {MULT}                 {yybegin(PLANS_MODE); return GwendolenTypes.MULT;}
<PLANS_MODE> {PLUS}                 {yybegin(PLANS_MODE); return GwendolenTypes.PLUS;}
<PLANS_MODE> {MINUS}                {yybegin(PLANS_MODE); return GwendolenTypes.MINUS; }
<PLANS_MODE> {LESS}                 {yybegin(PLANS_MODE); return GwendolenTypes.LESS;}
<PLANS_MODE> {EQUAL}                {yybegin(PLANS_MODE); return GwendolenTypes.EQUAL;}
<PLANS_MODE> {IDPUNCT}              {yybegin(PLANS_MODE); return GwendolenTypes.IDPUNCT;}
<PLANS_MODE> {RULEARROW}            {yybegin(PLANS_MODE); return GwendolenTypes.RULEARROW;}
<PLANS_MODE> {TRUE}                 {yybegin(PLANS_MODE); return GwendolenTypes.TRUE;}
<PLANS_MODE> {PL_CONST}             {yybegin(PLANS_MODE); return GwendolenTypes.PL_CONST;}
<PLANS_MODE> {NUMBER}               {yybegin(PLANS_MODE); return GwendolenTypes.NUMBER;}
<PLANS_MODE> {QUOTED_STRING}        {yybegin(PLANS_MODE); return GwendolenTypes.QUOTED_STRING;}









