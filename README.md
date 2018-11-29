# IBDAnalyst 
A tool help you analyze the recommended stock lists from <a href="www.investors.com">Investor Business Daily</a>.

<br/>
<h3>Major Process</h3> 
Scan -> Analyze -> Filter -> Enrich -> Print
<br/>
Scan    - extract the data from spreadsheet <br/>
Analyze - analyze the data based on different strategy <br/>
Filter  - filter the result <br/>
Enrich  - add additional analyze data to the result <br/>
Print   - generate the result spreadsheet <br/>

During Scan / Analyze / Print steps, you can only apply one implementation.
During Filter / Enrich steps, you can apply multiple implementation.

To run the program, refer to BasicAnalyst and GoldenAnalyst.
Both have the entry point to generate different result spreadsheets.

Before any commit, please run GoldFileTest.  

