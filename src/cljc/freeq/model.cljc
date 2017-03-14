(ns freeq.model
    (:require [schema.core :as s]))

(s/defschema Request {:_id s/Str
              :title s/Str
              :desc s/Str
              :likes s/Int})

(s/defschema PostRequest (select-keys Request [:title :desc]))

(s/defschema Comment {:_id s/Str
              :request-id s/Str
              :comment s/Str})

