using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using System.Collections;

public class PlayerCont : MonoBehaviour {

    private Animator animator;
    private SpriteRenderer spr;
    private bool derecha;
    public GameObject gameManager;
    public bool suelo;
    public float velocidad;
    public float salto;
    private Rigidbody rb;
    public float tolerancia;
    public float lastY;
    public int vidas;
    public bool inmortal;
    public Text texto;
    public Canvas img;
    public bool regenerando;

    // Use this for initialization
    void Start() {
        derecha = true;
        animator = this.gameObject.GetComponent<Animator>();
        spr = this.gameObject.GetComponent<SpriteRenderer>();
        suelo = true;
        rb = this.gameObject.GetComponent<Rigidbody>();
        vidas = 0;
        actualizarVidas(11);
        regenerando = false;
    }

    // Update is called once per frame
    void Update() {
        if (vidas > 0) {

            if (suelo)
            {
                if (Input.GetAxis("Horizontal") != 0)
                {
                    animator.Play("WalkRt");
                    if (Input.GetAxis("Horizontal") < 0)
                    {
                        derecha = true;
                        spr.flipX = true;
                    }
                    else
                    {
                        derecha = false;
                        spr.flipX = false;
                    }
                }
                else
                {
                    if (Input.GetButton("Fire1"))
                    {
                        animator.Play("Fire");
                        gameManager.GetComponent<GameManager>().disparar(derecha, transform.position.x, transform.position.y);
                    }
                    else
                    {
                        if (Input.GetAxis("Vertical") < 0)
                        {
                            animator.Play("Duck");
                        }
                        else
                        {
                            if (Input.GetAxis("Vertical") > 0)
                            {
                                rb.AddForce(new Vector3(0, 1) * salto);
                                animator.Play("Jump");
                                suelo = false;

                            }
                            else
                            {
                                animator.Play("Idle");
                            }
                        }
                    }
                }
            }


        }
        else
        {
            if (!inmortal)
            {
                animator.Play("Boom");
                rb.isKinematic = true;
                Invoke("muerto", 1.3f);
                Destroy(this.gameObject,1.35f);
                
            }
            else
            {
                actualizarVidas(11);
            }

        }
        Vector3 fuerza = new Vector3(1f, 0) * Input.GetAxis("Horizontal") * velocidad * Time.deltaTime;
        //Debug.Log(fuerza);
        transform.position += fuerza;

        if (lastY < this.transform.position.y)
        {
            lastY = this.transform.position.y;
        }
        else
        {
            if (lastY - this.transform.position.y > 6)
            {
                actualizarVidas(vidas * (-1));
            }
        }
    }

    void OnTriggerEnter(Collider other)
    {
        if(other.tag == "vida")
        {
            actualizarVidas(6);
            Destroy(other.gameObject);
        }
    }

    public void muerto()
    {
        SceneManager.LoadScene(2);
    }

    public void actualizarVidas(int num)
    {
        vidas += num;
        int shields = (vidas - 1) * 10;
        if (shields < 0) shields = 0;
        texto.text = "Shields: " + shields + "%";
        switch(shields)
        {
            case 0:
            case 10:
                texto.color = new Color(153, 0, 0);
                break;
            case 30:
            case 20:
                texto.color = Color.yellow;
                break;
            default:
                texto.color = Color.green;
                break;
        }
        if(shields == 0)
        {
            img.gameObject.SetActive(true);
            if(!regenerando)Invoke("regen", 3);

        }
        else
        {
            img.gameObject.SetActive(false);
        }
    }

    public void regen()
    {
        regenerando = true;
        if (vidas < 5)
        {
            actualizarVidas(1);
            Invoke("regen", 3);
        }
        else
        {
            regenerando = false;
        }
    }


}
