(ns freeq.core
    (:require-macros [cljs.core.async.macros :refer [go]])
    (:require [reagent.core :as reagent :refer [atom]]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]))

(enable-console-print!)

(defn add-request []
   [:div :text "ss"]
  )
