var pairs =
{
"tuning":{"garbage":1,"suggestion":1}
,"garbage":{"collector":1}
,"collector":{"tuning":1,"-xx":1}
,"suggestion":{"virtual":1}
,"virtual":{"machines":1}
,"machines":{"large":1}
,"large":{"heaps":1}
,"heaps":{"using":1}
,"using":{"caching":1}
,"caching":{"java":1}
,"java":{"-xx":1}
,"-xx":{"disableexplicitgc":1,"useconcmarksweepgc":1}
,"disableexplicitgc":{"-xx":1,"instead":1}
,"useconcmarksweepgc":{"-xx:newsize=<1\u002F4":1}
,"-xx:newsize=<1\u002F4":{"total":1}
,"total":{"heap":1}
,"heap":{"size>":1}
,"size>":{"-xx:survivorratio=16":1}
,"-xx:survivorratio=16":{"note":1}
,"note":{"better":1}
,"better":{"-xx":1}
,"instead":{"calling":1}
,"calling":{"system.gc":1}
,"system.gc":{"helps":1}
,"helps":{"low":1}
,"low":{"pause":1}
,"pause":{"collector":1}
}
;Search.control.loadWordPairs(pairs);