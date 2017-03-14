(ns freeq.server
  (:require [clojure.java.io :as io]
            [compojure.core :refer [ANY GET PUT POST DELETE defroutes]]
            [compojure.route :refer [resources]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.gzip :refer [wrap-gzip]]
            [ring.middleware.logger :refer [wrap-with-logger]]
            [environ.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [freeq.model :refer [Request Comment]]
            )
  (:gen-class))

(defroutes routes
           (GET "/requests" _
             {:status  200
              :headers {"Content-Type" "application/edn"}
              :body    (str {:requests [{:_id   1
                                         :title "Request 1 title"
                                         :desc  "Description"
                                         :likes 2}
                                        {:_id   2
                                         :title "Request 2 title"
                                         :desc  "Description 2"
                                         :likes 2}
                                        ]})}
             )
           (POST "/add-request" PostRequest
             {:status 201}
             )
           (GET "/" _
             {:status  200
              :headers {"Content-Type" "text/html; charset=utf-8"}
              :body    (io/input-stream (io/resource "public/index.html"))})

           (resources "/"))

(def http-handler
  (-> routes
      (wrap-defaults api-defaults)
      wrap-with-logger
      wrap-gzip))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 10555))]
    (println "Starting server on port:" port)
    (run-jetty http-handler {:port port :join? false})))
