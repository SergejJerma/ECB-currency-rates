<#import "parts/common.ftl" as c>

<@c.page>
<h5 class="text-center">Exchange Rates of the Euro against ${currency.name?ifExists} (${currency.code?ifExists}) history </h5>

 <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="true" aria-controls="collapseExample">
 Choose history period
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
           <div class="input-group mb-3">
  <div class="input-group-prepend">
    <label class="input-group-text" for="range">Options</label>
  </div>
  <select class="custom-select" id="range" name = "range">
    <option value="1">1m</option>
    <option value="3">3m</option>
    <option value="6">6m</option>
    <option value="YTD">YTD</option>
    <option value="12">1y</option>
    <option value="all">All</option>
  </select>
</div>         
            <div class="form-group">
              <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </form>
    </div>
</div>




<table class="table table-striped">
    <thead>
    <tr>
        <th class="text-center" scope="col">Currency rate</th>
        <th class="text-center" scope="col">Date</th>
    </tr>
    </thead>
    <tbody>
   
    <#list historyRates?ifExists as rate>
        <tr>
            <td class="text-center">${rate.rate}</td>
            <td class="text-center">${rate.date}</td>
        </tr>
    </#list>

    </tbody>
</table>

</@c.page>