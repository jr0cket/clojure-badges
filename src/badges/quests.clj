(ns badges.quests)


;; Started by trying to define what data a quest would have, a quest being a series of one or more challenges.

;;;(def quest {:challenge "fish" :level {:badges [""]}})

(def fish-quest {:quest-name "fishing"
                 :level [{:beginner "Hoek"}
                         {:intermediate "Haddock"}
                         {:hard "Cod"}]})

;; {:quest "fishing" :level [1 2 3]}

(def fish-badge {:points 200
                 :skill-level 1})

(def fish-advanced-badge {:points 300
                          :skill-level 2
                          :dependencies [fish-badge]})

;; badges have points, skill levels
;; each levels has different points
;; badges have dependencies (pre-requisites)

(def phil {:badges ["Hoek"]})

(defn start-quest
  [quest quester]
  (let [badges (get phil :badges)]
    (if (some #( = "Hoek" %1) badges)
      "You can now do haddock"
      "something fishy here")))

;; (start-quest fish-quest phil)


;; (phil :badges)

;; (let [badges (get phil :badges)]
;;   badges)["Hoek"]

;; (contains? (phil :badges) 0)


;;;;;;;;;;;;;;;;;;;;;
;; Modeling learning paths

;; the learner wants to take a learning path
;; -- pre-requisits (which paths should the learner have already taken
;; -- skills they will learn
;; -- topic tags 

;; a learning path represents a collection of modules
;; -- learning path has a description
;; -- skill level
;; 
