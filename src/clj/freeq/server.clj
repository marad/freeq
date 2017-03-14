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
            [freeq.mongo]
            [freeq.requests :as requests]
            [mount.core :as mount])
  (:gen-class))

(defroutes routes
           (GET "/requests" _
             {:status  200
              :headers {"Content-Type" "application/edn"}
              :body    (str {:requests (requests/list-requests)})})

           (POST "/add-request" req
                 (let [body (-> req :body slurp read-string)]
                   (requests/add-request body))
                 {:status 201})

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
  (println "Started states: " (mount/start))
  (let [port (Integer. (or port (env :port) 10555))]
    (run-jetty http-handler {:port port :join? false})))

(comment
  (def server (-main))
  (.stop server))
