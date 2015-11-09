(ns badges.routes.home
  (:require [badges.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [badges.quests :refer :all]))

(defn home-page []
  (layout/render
   "home.html" {:docs (-> "docs/docs.md"
                          io/resource
                          slurp)}))

(defn about-page []
  (layout/render "about.html"))

;; Defined a new page four our first quest
(defn fish-quest-page []
  (layout/render
   "quest.html" {:docs (start-quest fish-quest phil)}))

;; added our new page to the home-routes
(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page))
  (GET "/quest" [] (fish-quest-page)))
