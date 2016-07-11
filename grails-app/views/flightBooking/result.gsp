<h3>Search Result</h3>

<div class="provider-list jumbotron">
    <table class="table">
        <tbody>
        <g:if test="${list && list.size() > 0}">
            <g:each in="${list}" var="result">
                <tr>
                    <td><table style="width: 100%;">
                        <tr><td></td><td>${result.departure_time}</td><td></td></tr>
                        <tr><td>${result.airplane}</td><td>${result?.departure_air_port}</td><td>${result?.arrival_air_port}</td><td><button
                                type="button" class="btn btn-default">Select</button></td></tr>
                        <tr><td></td><td>${result.departure_time}</td><td></td></tr>
                    </table></td>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <h3>No result for for your search criteria</h3>
        </g:else>
        </tbody>
    </table>
</div>

