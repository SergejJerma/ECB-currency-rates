<#import "parts/common.ftl" as c>

<@c.page>
<h5 class="text-center">Exchange Rates of the Euro against Foreign Currencies (${date?ifExists}) </h5>

<table class="table table-striped">
    <thead>
    <tr>
        <th class="text-center" scope="col">Currency code</th>
        <th class="text-center" scope="col">Currency name</th>
        <th class="text-center" scope="col">Currency rate</th>
        <th class="text-center" scope="col">Date</th>
    </tr>
    </thead>
    <tbody>
   
    <#list currentRates?ifExists as currentRate>
        <tr>
            <td class="text-center">${currentRate.currency.code}</td>
            <td class="text-center"><a href="/history?id=${currentRate.currency.id}">${currentRate.currency.name}</a></td>
            <td class="text-center">${currentRate.rate}</td>
            <td class="text-center">${currentRate.date}</td>  
        </tr>
    </#list>
</@c.page>