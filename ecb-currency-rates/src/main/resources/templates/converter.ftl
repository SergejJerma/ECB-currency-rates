<#import "parts/common.ftl" as c> <@c.page>
<h5>Currency Converter</h5>
<div class="form-group col-md-6">
	<form method="post" action="/converter" class="form-control">
		<#if !convertedAmount??>
		<div class="form-group">
			<div class="input-group mb-3">
				<label class="input-group-text" for="inputGroupSelect01">Choose
					currency</label> <select class="custom-select mr-sm-2 form-control"
					id="code" name="code" required="required">

					<#list currenciesList?ifExists as currency>
					<option value="${currency.code}">${currency.name}</option>
					</#list>

				</select>
			</div>
		</div>
		<div class="form-group">

			<div class="input-group mb-3">

				<div class="input-group-prepend">
					<span class="input-group-text">EUR</span> <input type="number"
						name="amount" id="amount" class="form-control"
						placeholder="Enter amount to convert" min=1 required />
					<div class="input-group-append">
						<span class="input-group-text">.00</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary ml-2">Convert</button>
			</div>
		</#if>
		<#if convertedAmount??>

		<table class="table table-striped">
			<tbody>
			<thead>


			</thead>
			<tr>
				<th scope="row">Choosed currency</th>
				<td>${currentRate.currency.name?ifExists}
					(${currentRate.currency.code?ifExists})</td>

			</tr>
			<tr>
				<th scope="row">Entered amount</th>
				<td>${enteredAmount?ifExists} EUR</td>

			</tr>
			<tr>
				<th scope="row">Converted amount</th>
				<td>${convertedAmount?ifExists}
					${currentRate.currency.code?ifExists}</td>

			</tr>
			<tr>
				<th scope="row">Exchange rate</th>
				<td>${currentRate.rate?ifExists}</td>
			</tr>

			<tr>
				<th scope="row">Rate date</th>
				<td>${currentRate.date?ifExists}</td>

			</tr>
			</tbody>
		</table>
		</#if>
	</form>
</div>


</@c.page>