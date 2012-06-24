(ns madness.config)

(defonce config (eval (read-string (slurp "settings.clj"))))

(defn template []
  (str "templates/" (or (:template config) "default.html")))

(defn recent-posts [setting]
  (cond
   (= setting :columns) (or (-> config :recent-posts :columns) 3)
   (= setting :rows) (or (-> config :recent-posts :rows) 2)
   (= setting :total) (inc (* (recent-posts :columns)
                              (recent-posts :rows)))))

(defn dirs [role]
  (cond
   (= role :posts) (or (-> config :dirs :posts) "resources/posts")
   (= role :output) (or (-> config :dirs :output) "public/")))
