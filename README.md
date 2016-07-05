“Filtering rules”
In this assignment you should write a Java program that filters out rules and writes the result to XML
file. The result should contain one rule record for every rule name. Rules are provided in a XML input
file (expect that the test input file will be very large (>100MB)).

Input
The input file has the following format:
<rules>
<rule name=”…” type=”…” weight=”…”/>
<rule name=”…” type=”…” weight=”…”/>
…..
</rules>

name - The rule name (not unique in input, but should be unique in output)

type - Rule type can be one of 3 values: root, sub and child. The child rule is the most
important; the sub is of average importance and then the root is the least important.

weight - The weight specifies the rule importance within same type. The greater the weight value is,
the more important the rule is. Weight is a positive integer

The Filtering:
- First, check the rule type. For the same name, the child rule should take precedence over the
sub rule and the sub rule over the root rule correspondingly.
- For the same rule name and type, check the rule weight. The rule with greater weight should
win.

Programming conditions:
- Use only standard JDK 1.6-1.7. The only allowed 3rd party library is JUnit. No other libraries
are allowed

Additional points can be obtained if:
- Project is configured with Maven 3
- Solution uses more than one thread
- Program has unit tests (JUnit)
- Program contains XSLT file that produces HTML out of the output XML
- Program logs (file or console) the execution process
- Methods and class(es) have good JavaDoc coverage

Sample Input:
<rules>
<rule name=”a” type=”child” weight=”17”/>
<rule name=”a” type=”root” weight=”29”/>
<rule name=”b” type=”sub” weight=”56”/>
<rule name=”c” type=”child” weight=”99”/>
<rule name=”a” type=”sub” weight=”12”/>
<rule name=”c” type=”sub” weight=”99”/>
<rule name=”c” type=”root” weight=”99”/>
<rule name=”a” type=”child” weight=”34”/>
<rule name=”d” type=”root” weight=”45”/>
<rule name=”b” type=”sub” weight=”34”/>
</rules>
Sample output:
<rules>
<rule name=”a” type=”child” weight=”34”/>
<rule name=”b” type=”sub” weight=”56”/>
<rule name=”c” type=”child” weight=”99”/>
<rule name=”d” type=”root” weight=”45”/>
</rules>