http.request("http://127.0.0.1:10000/", "test")

local requesting = true

while requesting do 
	local event, url, sourceText = os.pullEvent()

	if event == "http_success" then
		local respondText = sourceText.readAll()
		sourceText.close()

		print(respondText)

		requesting = false

	elseif event == "http_failure" then
		print("server didn't respond.")

		requesting = false
	end
end
